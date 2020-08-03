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
				}catch (err)
					{
						echo 'DELETE FAILED'
					}
			sh deployProps.dockerPull
			sh deployProps.dockerRestart
			echo 'DEPLOY SUCCESS'				
		}
return this
