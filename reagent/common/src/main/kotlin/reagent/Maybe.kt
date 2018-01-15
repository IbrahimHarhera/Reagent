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

import kotlin.DeprecationLevel.HIDDEN

/** Emits an item, signals nothing (no item), or signals error. */
expect abstract class Maybe<out I>() : Observable<I> {
  abstract suspend fun produce(): I?

  @Deprecated("Optimized implementation for polymorphism.", level = HIDDEN)
  override suspend fun subscribe(emit: Emitter<I>)
}