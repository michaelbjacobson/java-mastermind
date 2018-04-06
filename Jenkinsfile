node {
    def app

    stage('Clone repo') {
        checkout scm
    }

    stage('Build image') {
        app = docker.build("mastermind")
    }

    stage('Push image') {
        sh 'chmod 777 ./script.sh'
        sh './script.sh'
        sh 'DOCKER_CONFIG=~/.docker'

        docker.withRegistry("https://445579089480.dkr.ecr.us-east-1.amazonaws.com", "ecr:us-east-1:aws-creds") {
           sh 'cat ~/.docker/config.json'
        }
    }
}