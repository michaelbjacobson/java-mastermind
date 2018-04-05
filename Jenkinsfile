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
        docker.withRegistry('https://index.docker.io/v1/', 'docker-hub-credentials') {
            app.push('latest')
        }
    }

}