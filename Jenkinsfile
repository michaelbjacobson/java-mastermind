node {
    def app

    stage('Clone repo') {
        checkout scm
    }

    stage('Build image') {
        app = docker.build("mastermind")
    }

    stage('Push image') {
        withPythonEnv('CPython-2.7'){
            pysh 'pip install awscli'
            pysh 'aws help'
        }
    }
}