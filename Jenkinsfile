node {
    def app

    stage('Clone repo') {
        sh 'docker logout https://445579089480.dkr.ecr.us-east-1.amazonaws.com'
        checkout scm
    }

    stage('Build image') {
        app = docker.build("mastermind")
    }
}