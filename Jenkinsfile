def SONAR_CE_TASK_URL
def appName = 'vanhack-api'
def registryHost = '127.0.0.1:30400/'
def imageName = "${registryHost}${appName}:${env.BUILD_ID}"
def namespace = 'applications'

pipeline {
    agent any 
    stages {


		 stage('Maven Build') {
             steps {
            	 withMaven(maven: 'M3', mavenSettingsConfig: 'maven-settings', mavenLocalRepo: '.repository') {
	      			 sh "mvn install -DskipTests"
            	 }
        	 }
		 }



	    stage('Unit Test') {
			steps {

				withMaven(maven: 'M3', mavenSettingsConfig: 'maven-settings') {
                   	sh 'mvn clean install -U'
					sh 'mvn test'
				}
			}
		}




  		stage('SonarQube analysis') {
  			steps {
    			withMaven(maven: 'M3', mavenSettingsConfig: 'maven-settings') {
      				sh 'mvn sonar:sonar -Dsonar.host.url=http://sonar.cloud.org -Dsonar.login=b128bcf69b805af6753555803568d9e07cc5c16d'
      				script {
					    def props = readProperties file: 'target/sonar/report-task.txt'
						SONAR_CE_TASK_URL = props.ceTaskUrl
						echo SONAR_CE_TASK_URL
					}
    			}
    		}
  		}



		stage('Quality Gate') {
			steps {
				script {
					timeout(time: 5, unit: 'MINUTES') {
						def ceTask
						waitUntil {
		       				sh "curl ${SONAR_CE_TASK_URL} -o ceTask.json"
		       				ceTask = readJSON file: 'ceTask.json'
		       				echo ceTask.toString()
		       				return "SUCCESS".equals(ceTask.task.status)
		     			}
		     			def qualityGateUrl = "http://sonar.cloud.org/api/qualitygates/project_status?analysisId=" + ceTask.task.analysisId
		      			sh "curl $qualityGateUrl -o qualityGate.json"
		      			def qualityGate = readJSON file: 'qualityGate.json'
		      			echo qualityGate.toString()
		      			if ("ERROR".equals(qualityGate.projectStatus.status)) {
		      				// error  "Quality Gate failure"
		      				echo '<<< "Quality Gate failure" >>>'
		      			}
					}
				}
			}
		}


	    stage('Docker Build') {
			steps {
				script {
					def customImage = docker.build("127.0.0.1:30400/${appName}:${env.BUILD_ID}", ".")
    				customImage.push()
    			}
			}
		}



		stage ('deploy') {
            steps {
                sh "echo ${appName} ${imageName} ${namespace}"
                sh "kubectl set image deployment ${appName} ${appName}=${imageName} -n ${namespace}"
                sh "kubectl rollout status deployment/${appName} -n ${namespace}"
            }
        }





    }
}
