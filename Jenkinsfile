node {
    def app

    stage('Clone repo') {
        checkout scm
    }

    stage('Build image') {
        app = docker.build("michaeljacobson/mastermind")
    }

    stage('Run unit tests') {
        app.inside {
            sh 'mvn test'
        }
    }

    stage('Push image') {
        app.push('latest')
    }

}