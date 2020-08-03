def build(){
			try{
				dir(gitProps.path){
					sh commonProps.mavenClean
					sh deployProps.dockerBuild
					status = "SUCCESSFUL"
					echo 'BUILD SUCCESS'
						}
					}
			catch (e) {
					status = "FAILED"
						} 
	}
def dockerPush(){
			try{
				sh deployProps.dockerLogin
				sh deployProps.dockerPush
				status = "SUCCESSFUL"
				echo deployProps.dockerPush
				echo 'push SUCCESS'
						}
			catch (e) {
					status = "FAILED"
						} 
		}
def deploy(){
			try	{
				sh deployProps.dockerContainerId
				output=readFile('result').trim()
				if(output!=null)
					{
						sh deployProps.dockerContainerRm
					}
				sh 'sudo chmod 666 /var/run/docker.sock'
				sh deployProps.dockerPull
				sh deployProps.dockerRestart
				}catch (err)
					{
						echo 'DELETE FAILED'
					}
			echo 'DEPLOY SUCCESS'				
		}
return this
