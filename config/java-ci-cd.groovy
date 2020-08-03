def build(){
			try{
				dir(deployProps.dockerPull){
					sh commonProps.mavenClean
					status = "SUCCESSFUL"
					echo 'BUILD SUCCESS'
						}
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
			sh deployProps.dockerDeploy
			sh deployProps.dockerRestart
			echo 'DEPLOY SUCCESS'				
		}
return this
