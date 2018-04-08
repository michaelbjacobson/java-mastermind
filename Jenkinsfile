node {
    def app

    stage('Clone repo') {
        checkout scm
    }

    stage('Build image') {
        app = docker.build("mastermind")
        sh 'cd usr/bin; ls -alt'
    }
}