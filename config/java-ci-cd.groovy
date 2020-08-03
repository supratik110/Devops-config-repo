def build(){
			try{
				dir(gitProps.path){
					sh commonProps.mavenClean
					status = "SUCCESSFUL"
					sh deployProps.dockerBuild
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
			sh deployProps.dockerRestart
			echo 'DEPLOY SUCCESS'				
		}
return this
