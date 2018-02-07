package reagent.operator

import reagent.runTest
import reagent.source.emptyObservable
import reagent.source.observableOf
import reagent.tester.testTask
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.fail

class ObservableFoldTest {
  @Test fun empty() = runTest {
    emptyObservable()
        .fold("Hello") { _, _ -> fail() }
        .testTask {
          item("Hello")
        }
  }

  @Test fun one() = runTest {
    observableOf("Item")
        .fold("Seed") { accumulator, item ->
          assertEquals("Seed", accumulator)
          assertEquals("Item", item)
          "Return"
        }.testTask {
          item("Return")
        }
  }

  @Test fun multiple() = runTest {
    observableOf(1, 2, 3)
        .fold(0) { accumulator, item -> accumulator + item }
        .testTask {
          item(6)
        }
  }
}
