node {
    def app
    def siteHttpCode = sh (
        script: 'curl -s -o /dev/null -w "%{http_code}" https://java-mastermind.com/'
        returnStdout: true
    ).trim()

    stage('Clone repo') {
        echo "${siteHttpCode}"
        checkout scm
    }

    stage('Build Docker image') {
        app = docker.build("mastermind")
    }

    stage('Run unit tests') {
        app.inside {
            sh 'mvn -Dtest=Fast* test'
        }
    }

    stage('Push Docker image') {
        docker.withRegistry("https://445579089480.dkr.ecr.us-east-1.amazonaws.com", "ecr:us-east-1:aws-credentials") {
            app.push('latest')
        }
    }

    if (siteHttpCode == '200') {
        stage('Update ECS service') {
            sh 'sudo terraform init -input=false'
            sh 'sudo terraform taint aws_ecs_service.mastermind_service'
            sh 'sudo terraform plan -out=tfplan -input=false'
            sh 'sudo terraform apply -input=false tfplan'
        }
    }
}
