node {
    def app

    stage('Clone repo') {
        checkout scm
    }

    stage('Build image') {
        app = docker.build("mastermind")
    }

    stage('Push image') {
        app.inside {
            sh 'pwd'
            sh 'ls -alt'
        }
    }
}