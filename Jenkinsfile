node {
    def app

    stage('Clone repo') {
        checkout scm
    }

    stage('Build image') {
        app = docker.build("mastermind")
    }

    stage('Push image') {
        sh 'rm -rf ~/.docker'
        sh 'mkdir ~/.docker'
        sh 'touch ~/.docker/config.json'
        sh 'echo "test" >> ~/.docker/config.json'
        sh 'cat ~/.docker/config.json'

        docker.withRegistry("https://445579089480.dkr.ecr.us-east-1.amazonaws.com", "ecr:us-east-1:aws-creds") {

        }
    }
}