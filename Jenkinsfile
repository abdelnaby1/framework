pipeline{

    agent any

    stages{

        stage('Build Jar'){
            steps{
                sh "mvn clean package -DskipTests"
            }
        }

        stage('Build image'){
            steps{
                sh "docker build -t=abdelnaby1/selenium ."
            }
        }

        stage('Push the image'){
            environment{
                DOCKER_HUB = credentials('dockerhub-credentials')
            }
            steps{
                sh 'echo ${DOCKER_HUB_PSW} | docker login -u ${DOCKER_HUB_USR} --password-stdin'
                sh "docker push abdelnaby1/selenium"
            }
        }

    }
    post{
        always{
            sh "docker logout"
        }
    }
}