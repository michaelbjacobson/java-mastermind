node {
    def app

    stage('Clone repository') {
        checkout scm
    }

    stage('Build Docker image') {
        app = docker.build("michaeljacobson/mastermind")
    }

    stage('Unit test Docker image') {
        app.inside {
            sh 'mvn test'
        }
    }

    stage('Push Docker image to ECR') {
        docker.withRegistry('https://445579089480.dkr.ecr.eu-west-2.amazonaws.com', 'ecr:eu-west-2:mastermind-ecr-credentials') {
            app.push("${env.BUILD_NUMBER}")
            app.push("latest")
        }
    }
}