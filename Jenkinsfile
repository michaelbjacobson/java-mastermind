node {
    def app

    stage('Clone repo') {
        checkout scm
    }

    stage('Build image') {
        app = docker.build("mastermind")
    }

    stage('Run unit tests') {
        app.inside {
            sh 'mvn test'
        }
    }

    stage('Push image') {
        stage('Push image') {
            sh 'cd $JENKINS_HOME; rm -rf .docker; mkdir .docker; cd .docker; touch config.json; echo {"auths":{}} >> config.json'
            sh 'cd ~; rm -rf .docker; rm -rf .dockercfg; mkdir .docker'

            docker.withRegistry("https://445579089480.dkr.ecr.us-east-1.amazonaws.com", "ecr:us-east-1:aws-credentials") {
                sh 'cp $JENKINS_HOME/.docker/config.json ~/.docker/config.json'
                sh 'sed -e "s#https://##g" -i ~/.docker/config.json'
                sh 'docker login -u aws -p lsb2FkIjoiTmRRMnhSVlZwNUZ6ak9DdktFS0VDUDVXTTNHVjN5TnBrMWhIeTZaQ0lnK2ZVV2lPTEpaYWRZbGprbUQrYUhkU1AvNUtGRXhmNDdkNThWU3hBMjNaNEp6U0dEc0p5N1NQajArT2RVMUVEZGpDeFpEWitoYUFRcHo4bTdZNk9Xb2xUdTZTcXBsMi9tN2RVKy9IdnNKWUo1QVdqWUlzd2Z5aFV3VisrTnBmNVd1MW9HOUdEN2RKcS9LUnJNV1o3a1BKelBQK1AwalFJN2ZhSEtQeXVFS1ZiKzg4S05YY2tGS3ZUTlROR2xaUHhRQTd2WEVWTlRxLzVGcFQ1dXBHQ1l4ZDFDbWpnMEQyWWFJUTR6VHlnUEgxS0NQdUpJaExuNTMxSjZIQlE1KytHeU1QZUNtWVZibnpiNytrcy9MWDdJQUhvT1FKbU55amViUGkrSTcrSy9DTHorN1d0UUNhQUlmclNvR09iNzFMZlJFQXpHWTJsZzZWSlpzd01Vd0YxNFNmZWlFTHRNZzYwd0tGa0pVZG11dDhIVHl6WC9mRytaTVdnSkZMcmZHR01zcFF6OWRPTUFTa3hRZ21aNkFtWUNrQU9mYTh3UE12OC9oNUdmMXRIWlRZZzBzTEVEN2JQRTJXazQwVDRQeDBYMUhQM1ZwOTdLUkRqUjQycGl0T1lSV2NTcktPNWc1UW11eEI5Vkc0ZUVCMGk1Yk8vSi9YUkFmazVpL3VmZkVnemJPRTZDZDRUa1dmU1l1aVVab1g0Nm9LZGdsd2RHdG4vNWJFdnR5akh0OXdORHpHc0lQNkk2VUY3TDJYZ0YvVFByU2REa1FuZWQya2RHajk3bzhuVU1NQWdrVXR0clpQdE1yQndyaFpoMlhkYllsRjYwY24yYm9SOW1LUFhCeGV5VnRWQWxzY3NHUjVLVXFrRVkydVpTdGxkbUZlTTg4NUY5MjZDN21DSEtTK0xxc3F6NFhpbTNINHlRR3l0OUJnaVdlY0NvUHV2YkdueVlEcDdsUnFRckltdWtwRU1MWklKSVZtUExETHMrVTJ5TUJNb2lwU29DQnQwODd5cStyeGZCcyt5WnVBL1RxWnB5djJQc1ZFbFdHSzJXSTIzbnlLZHY2amVNWmhiQ0JpZTZLTDBwSFJyek1Fdmduc3pFNEVyeFU4cHBEMlZuZHVGOENMb2xRdGNZRW96RkRhVCtReWUrc04rSVd2L0dhZEc1ZTZTRmlOWmpDYWZtZ0ZNeHJjVXVqVXN0ZnN4T2UrdmdNV3BSVWZwSG5US2dTSDZVeXpCa21WUXpqWmpNVy9seDhOTUpkQ2RqWEJpYXlqVVplMWRSbmF3SFBjbUhjS0p6TEZHcDRpaVdKK2k0Z0E0WHFmZmc4ZkxDdmN5Q1V3QmxZM1ArRFJRK2MrK1o4Q2YrNUNTLzhkSDRxRXlmVmxURWtRNWxrRFoxd1FjaFk9IiwiZGF0YWtleSI6IkFRRUJBSGh3bTBZYUlTSmVSdEptNW4xRzZ1cWVla1h1b1hYUGU1VUZjZTlScTgvMTR3QUFBSDR3ZkFZSktvWklodmNOQVFjR29HOHdiUUlCQURCb0Jna3Foa2lHOXcwQkJ3RXdIZ1lKWUlaSUFXVURCQUV1TUJFRUROMGQydmFrZmFFaXVQbEkwQUlCRUlBN0JsMXIyTXJuR3JKV1Yvb0lLT2xXay9oSTlZZ2RmRkhTbGdTUmZRVEluZkFwaDhrdmRQRFUzY0svckxjNDFQQW5EVmx2dXRrOE9Pbk5ycFU9IiwidmVyc2lvbiI6IjIiLCJ0eXBlIjoiREFUQV9LRVkiLCJleHBpcmF0aW9uIjoxNTIzMTkzNjQ1fQ== 445579089480.dkr.ecr.us-east-1.amazonaws.com'
                sh 'docker push 445579089480.dkr.ecr.us-east-1.amazonaws.com/mastermind:latest'
            }
        }
    }
}