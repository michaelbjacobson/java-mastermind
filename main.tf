provider "aws" {
  region = "us-east-1"
}

resource "aws_ecs_cluster" "ecs_cluster" {
  name = "mastermind-cluster"
}

resource "aws_autoscaling_group" "ecs_cluster_instances" {
  name = "ecs-cluster-instances"
  min_size = 1
  max_size = 1
  launch_configuration = "${aws_launch_configuration.ecs_instance.name}"
  vpc_zone_identifier = ["subnet-37b6181b"]
}

resource "aws_launch_configuration" "ecs_instance" {
  iam_instance_profile = "ecsInstanceRole"
  name_prefix = "ecs-instance-"
  instance_type = "t2.micro"
  security_groups = ["${aws_security_group.allow_all.id}"]
  image_id = "ami-cb17d8b6"
  key_name = "mastermind"
  user_data = <<-EOF
              #!/bin/bash
              echo ECS_CLUSTER="${aws_ecs_cluster.ecs_cluster.name}" >> /etc/ecs/ecs.config
              EOF
}

resource "aws_ecs_task_definition" "mastermind" {
  family = "mastermind"
  container_definitions = <<EOF
                          [{
                            "name": "mastermind",
                            "image": "445579089480.dkr.ecr.us-east-1.amazonaws.com/mastermind:latest",
                            "cpu": 1024,
                            "memory": 768,
                            "essential": true,
                            "portMappings": [{"containerPort": 4567, "hostPort": 4567}]
                          }]
                          EOF
}

resource "aws_ecs_service" "mastermind_service" {
  name = "mastermind-service"
  cluster = "${aws_ecs_cluster.ecs_cluster.id}"
  task_definition = "${aws_ecs_task_definition.mastermind.arn}"
  desired_count = 1

  placement_constraints {
    type       = "memberOf"
    expression = "attribute:ecs.availability-zone in [us-east-1a, us-east-1b, us-east-1c, us-east-1d]"
  }

  load_balancer {
    elb_name = "${aws_elb.mastermind_elb.id}"
    container_name = "mastermind"
    container_port = 4567
  }
}

resource "aws_elb" "mastermind_elb" {
  name = "mastermind-elb"
  availability_zones = ["us-east-1d"]
  security_groups = ["${aws_security_group.allow_all.id}"]

  listener {
    instance_port     = 4567
    instance_protocol = "http"
    lb_port           = 80
    lb_protocol       = "http"
  }

  listener {
    instance_port      = 4567
    instance_protocol  = "http"
    lb_port            = 443
    lb_protocol        = "https"
    ssl_certificate_id = "arn:aws:acm:us-east-1:445579089480:certificate/37cf8990-8d64-4e87-9c28-9e3c61c99a55"
  }

}

resource "aws_security_group" "allow_all" {
  name = "allow-all"

  ingress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }

  egress {
    from_port       = 0
    to_port         = 0
    protocol        = "-1"
    cidr_blocks     = ["0.0.0.0/0"]
  }
}

data "aws_route53_zone" "hosted_zone" {
  name = "java-mastermind.com."
}

resource "aws_route53_record" "www" {
  zone_id = "${data.aws_route53_zone.hosted_zone.zone_id}"
  name    = "www.${data.aws_route53_zone.hosted_zone.name}"
  type    = "A"
  alias {
    name = "dualstack.${aws_elb.mastermind_elb.dns_name}"
    zone_id = "${aws_elb.mastermind_elb.zone_id}"
    evaluate_target_health = false
  }
}

resource "aws_route53_record" "blank" {
  zone_id = "${data.aws_route53_zone.hosted_zone.zone_id}"
  name    = "${data.aws_route53_zone.hosted_zone.name}"
  type    = "A"
  alias {
    name = "dualstack.${aws_elb.mastermind_elb.dns_name}"
    zone_id = "${aws_elb.mastermind_elb.zone_id}"
    evaluate_target_health = false
  }
}