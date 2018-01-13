/*
 * Copyright 2016 Jake Wharton
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package reagent

import reagent.operator.map
import reagent.source.emptyMaybe
import reagent.source.maybeOf
import reagent.source.toMaybe
import reagent.tester.testMaybe
import kotlin.test.Test

class MaybeMapTest {
  @Test fun map() = runTest {
    maybeOf("Hello")
        .map(String::toUpperCase)
        .testMaybe {
          item("HELLO")
        }
  }

  @Test fun mapEmpty() = runTest {
    emptyMaybe<Nothing>()
        .map { throw AssertionError() }
        .testMaybe {
          nothing()
        }
  }

  @Test fun mapError() = runTest {
    val exception = RuntimeException("Oops!")
    exception.toMaybe<Nothing>()
        .map { throw AssertionError() }
        .testMaybe {
          error(exception)
        }
  }

  @Test fun mapThrowing() = runTest {
    val exception = RuntimeException("Oops!")
    maybeOf("Hello")
        .map { throw exception }
        .testMaybe {
          error(exception)
        }
  }
}
