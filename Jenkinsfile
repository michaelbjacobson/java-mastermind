node {
    def app

    stage('Clone repo') {
        checkout scm
    }

    stage('Build image') {
        sh 'export DOCKER_CONFIG=$JENKINS_HOME/.docker'

        app = docker.build("mastermind")
    }

    stage('Run unit tests') {
        app.inside {
            sh 'mvn test'
        }
    }

    stage('Push image') {
        sh 'printenv'

        docker.withRegistry("https://445579089480.dkr.ecr.us-east-1.amazonaws.com", "ecr:us-east-1:aws-credentials") {
            app.push("latest")
        }
    }
}