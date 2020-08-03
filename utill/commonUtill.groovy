pipeline {
	agent any
        	stages {
		stage('LOAD PROPERTIES FILES') {
                  steps {
                       script {
		       	commonProps = readProperties file:'properties/common.properties'
			gitProps = readProperties file:'properties/git.properties'
			deployProps = readProperties file:'properties/deploy.properties'
			configProp = load commonProps.configFile
			echo 'LOAD SUCCESS'
				}
			}
			}
			stage('READ GIT') {
                  steps {
		  	script {
			git url: gitProps.gitUrl,
			branch: gitProps.branchName
			echo 'READ SUCCESS'
				}
					}
				}
			stage('BUILD') {
                  steps {
		  	script {
			configProp.build()
			}
				}
			}
			stage('DEPLOY') {
                  steps {
		  	script {
			configProp.deploy()
			deleteDir()
			}
                }         
            }    
     	}
}
