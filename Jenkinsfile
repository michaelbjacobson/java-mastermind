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
                docker login -u AWS -p eyJwYXlsb2FkIjoiQVp5UFpHN1NEaGs5OGM4UEVNUVROWk11Lzkva3BTemZaQTNWTXhHYVJWN0VkRXl6d0N3SVNFNVRmVFp0c3RiUm1yazVlelZHN0FhbzdmbzR5QnhESlMraEFDamJVVUlLSzV5WmVRQ3RPRDJsVnQ3MFR1SFhUV3NSMU9nc01aeEt2cmIrZmFJS0diMTQ0d3VJZ0F1cjczakhoR2NNMWlNN1RLSGQ3alNLektOaXBnTTY3TXdsWUZBeW1vRWpmb3pvdTVIcTRNSHNsVXB6NmNDckwvOFY3L1ZYeGZ4VGFtQ0h0RWRLSHZ4QVVBaU5qajBaa0ZkUHYrOUFaUWx3MEluLzJCZzlxVVJyYklsNERzNVdFVlhKYkhZekN0MVBVYXhBTERvM1hGbkJoL1JIcUk1My9KL0NLS003OTVON2FYMmlvazhKZTdRbS91bXJ5QTdHclZKM2xOWlhEU3lIYnJyNjRmT3JUTUM2MzhzeUM1V2xaUDhCNzVEaXc0OVdycWxpRlVDWkI2b0ZWZDQvSWNhS2dGUUpaVnduZTRTZUo5bzVGVlcwNUV6Z2JaRlhzWmw2Q0xzZ2NQcldhcGUxRkNGeWVOaHFRS0dOY1BFenVTaEtnbVIvRHVNTHBYUjViTm04TkR6SDFHOTY1TG5jN1R5Z2hCQ2hsdm50aEl6V0hXNXloK2JsYkJPbVdmcVhkTEViYkFJR2ZpVlUvSm52RWpVT1VLRkgxUmdZeTR3bXo5bHF3TVZTT1MxaHZwK0d0UVc1dXBWNkRPL1FISmJBWmR1Tjl1T1Q3eFZ1MzZ0RGlRaHgxQThGUVB4SEc4UStOSExhQ2hRN3g4NmRGRElCOVhCWWNHWHhrNitDV0U0UWsrZXhJSVhMc29GazJ3cG0xVnZGQkZKLzZMK1VSTEFrYmw1dkZoODRrdWwxU1hVRlMyRm9TVEFlQ2pEOFJlNzRCb2FJZzRPdkZjeEtYWXpHQ1N2RThudXk5bktoT09kRUk1TXFVZUk3L2NlUFJpRlRSRkVhYkp1R3BNVjRTY1Yxc2VZekRHc3B0cWhVNmpiQVZUM0tIUEFSL0paM1E3bUpRcGxXYmU5aHkrS3JHcHdMTGJSMHZJaVYvYlN0b1JaUzNVZFVuWTNzczBUd0pmRDNNQVE3VzgzVzBranhscjg4a0dmMXVRcmtoOUtxODVmUkVTUXhXZTlrREx5U25rN3NucWhSVFF5QXUrWGsycUxVYS9YN1VBbEdTZm1mbWFvQStHa2liZmdhQ3gxTys1dTR4R08vM1ZZZTMzYmc0ZUZlSGhqTGJrSDRNMnFaNzZBNjVveG82SGE5Q0s1d2gvUEVPb2lRSEx2T2dzRGhvVzNBcVNtclN4NjI3d1JiWFB5VGpOek9lKzltUExGbkRDRXZuNG1wM1hmSDlXeGNJbnlnWXM1eGFCRHozV0VoaHVNV3RiWGZ5ajA9IiwiZGF0YWtleSI6IkFRRUJBSGh3bTBZYUlTSmVSdEptNW4xRzZ1cWVla1h1b1hYUGU1VUZjZTlScTgvMTR3QUFBSDR3ZkFZSktvWklodmNOQVFjR29HOHdiUUlCQURCb0Jna3Foa2lHOXcwQkJ3RXdIZ1lKWUlaSUFXVURCQUV1TUJFRUROdjdwTkpFUk16cGQwTzZtUUlCRUlBN0p2cm5mSW93aW9raUozeW5DSklkSU14UHJzdHFhLytrT2NaUmxUWVZrVENQNE9nRHdDcENVT0pyRnNZaXJRQlBwZ3hIckMvZXNabDNaSHc9IiwidmVyc2lvbiI6IjIiLCJ0eXBlIjoiREFUQV9LRVkiLCJleHBpcmF0aW9uIjoxNTIzMjMxODQxfQ== https://445579089480.dkr.ecr.us-east-1.amazonaws.com
                sh 'docker push 445579089480.dkr.ecr.us-east-1.amazonaws.com/mastermind:latest'
            }
        }
    }
}