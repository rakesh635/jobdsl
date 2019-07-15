job('test-job') {
    scm {
      git('$repourl')
    }
    triggers {
        scm('*/15 * * * *')
    }
    steps {
        maven('-e clean test')
    }
}
job('DSL-Tutorial-1-Test') {
    scm {
        git('https://github.com/rakesh635/hello-phpunit-world.git')
    }
    triggers {
        scm('H/15 * * * *')
    }
    steps {
        maven('-e clean test')
    }
}
