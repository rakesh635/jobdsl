if("$LANG" == "PHP" || "$LANG" == "php")
{
    job("$pipelinename"+"_phpjob") {
        logRotator(-1, 10)
        scm {
            git {
                remote {
                    name('Repo URL')
                    url("$repourl")
                    branch("$branch")
                }
                extensions {
                    cleanAfterCheckout()
                }
            }
        }
        triggers {
            scm('H/5 * * * *')
        }
        steps {
            
        }
    }
}
else if("$LANG" == "Java" || "$LANG" == "JAVA" || "$LANG" == "java")
{
    def Jenkinsfile = readFileFromWorkspace('java/Jenkinsfile')
    Jenkinsfile = Jenkinsfile.replaceAll('--REPOURL--',"$repourl")
    Jenkinsfile = Jenkinsfile.replaceAll('--REPOBRANCH--',"$branch")
    
    pipelineJob("$pipelinename")
    {
        def repo = "$repourl"
        triggers {
            scm('H/5 * * * *')
        }
        description("Pipeline for $repo")
        definition {
            cps {
                script(Jenkinsfile)
                sandbox(true)
            }
        }
    }
    
    job("$pipelinename"+"_job") {
        logRotator(-1, 10)
        jdk('defaultJDK')
        scm {
            git {
                remote {
                    name('Repo URL')
                    url("$repourl")
                    branch("$branch")
                }
                extensions {
                    cleanAfterCheckout()
                }
            }
        }
        triggers {
            scm('H/5 * * * *')
        }
        steps {
            maven {
                goals('clean')
                goals('install')
                mavenOpts('-Xms256m')
                mavenOpts('-Xmx512m')
                properties(skipTests: true)
                mavenInstallation('maven3.3.9')
            }
        }
        publishers {
            archiveArtifacts('target/hello-world-war-1.0.0.war')
        }
    }
}
