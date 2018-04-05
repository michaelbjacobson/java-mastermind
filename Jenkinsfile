node {
    def app

    stage('Clone repo') {
        checkout scm
    }

    stage('Build Docker image') {
        app = docker.build("mastermind")
    }

    stage('Run unit tests') {
        app.inside {
            sh 'mvn test'
        }
    }

    stage('Push image') {
        app.inside { sh "eval $(aws ecr get-login | sed 's|https://||')" }
        docker.withRegistry('https://445579089480.dkr.ecr.eu-west-2.amazonaws.com') {
            app.push("latest")
        }
    }

}