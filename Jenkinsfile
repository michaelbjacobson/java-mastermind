node {
    def app

    stage('Clone repo') {
        checkout scm
    }

    stage('Build image') {
        app = docker.build("mastermind")
    }

    stage('Push image') {
        sh 'apt install python-pip'
        sh 'pip install awscli'
        sh 'aws help'
    }
}