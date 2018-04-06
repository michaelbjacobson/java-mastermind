node {
    def app

    stage('Clone repo') {
        sh 'pwd'
        checkout scm
    }

    stage('Build image') {
        app = docker.build("mastermind")
    }

    stage('Push image') {
        sh 'sudo rm -rf ~/.docker'
        sh 'sudo mkdir ~/.docker'
        sh 'sudo touch ~/.docker/config.json'
        sh 'sudo cat ~/.docker/config.json'

        docker.withRegistry("https://445579089480.dkr.ecr.us-east-1.amazonaws.com", "ecr:us-east-1:aws-creds") {
            sh 'cat ~/.docker/config.json'
        }
    }
}