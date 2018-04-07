node {
    def app

    stage('Clone repo') {
        checkout scm
    }

    stage('Build image') {
        app = docker.build("mastermind")
    }

    stage('Push image') {
        withPythonEnv('python'){
            pysh 'pip install awscli'
            pysh 'aws help'
        }
    }
}