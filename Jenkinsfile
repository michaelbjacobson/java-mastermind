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
        withCredentials([usernamePassword( credentialsId: 'docker-hub-credentials', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
            docker.withRegistry('', 'docker-hub-credentials') {
                sh "docker login -u ${USERNAME} -p ${PASSWORD}"
                myImage.push("${env.BUILD_NUMBER}")
                myImage.push("latest")
            }
        }
    }

}