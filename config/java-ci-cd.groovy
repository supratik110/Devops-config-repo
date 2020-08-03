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
			echo 'PUSH SUCCESS'	
		}
def deploy(){
			try	{
				sh deployProps.dockerContainerId
				output=readFile('result').trim()
				if(output!=null)
					{
						sh deployProps.dockerContainerRm
					}
				sh deployProps.dockerLogin
				sh deployProps.dockerPull
				sh deployProps.dockerBuild
				sh deployProps.dockerRestart
				}catch (err)
					{
						echo 'DELETE FAILED'
					}
			echo 'DEPLOY SUCCESS'				
		}
return this
