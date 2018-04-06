node {
    def app

    stage('Clone repo') {
        checkout scm
    }

    stage('Build image') {
        sh 'cat ./.config.json'
        app = docker.build("mastermind")
    }
}