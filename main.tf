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

resource "aws_ecs_service" "mastermind" {
  name = "mastermind"
  cluster = "${aws_ecs_cluster.ecs_cluster.id}"
  task_definition = "${aws_ecs_task_definition.mastermind.arn}"
  desired_count = 1

  load_balancer {
    elb_name = "${aws_elb.mastermind_elb.id}"
    container_name = "mastermind"
    container_port = 4567
  }
}

resource "aws_elb" "mastermind_elb" {
  name = "mastermind-elb"
  availability_zones = ["us-east-1d"]
  listener {
    lb_port = 80
    lb_protocol = "http"
    instance_port = 4567
    instance_protocol = "http"
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