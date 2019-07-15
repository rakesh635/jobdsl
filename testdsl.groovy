job('test-job') {
    scm {
      git("$repourl")
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
        git("$repourl")
    }
    triggers {
        scm('H/15 * * * *')
    }
    steps {
        maven('-e clean test')
    }
}
