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
                sh "docker build -t=abdelnaby/selenium ."
            }
        }

        stage('stage-3'){
            steps{
                echo "pushing docker image"
            }
        }

    }
}