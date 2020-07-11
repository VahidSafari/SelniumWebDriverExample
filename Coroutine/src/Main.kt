import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.actor
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val customScope = CustomScope()

    /*val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        println(throwable.javaClass.simpleName)
    }

    val job = customScope.launch(coroutineExceptionHandler) {
        throw ArithmeticException()
    }
    val deferred = customScope.async(coroutineExceptionHandler) {
        throw ArithmeticException()
    }
    deferred.await()*/

    customScope.launch(Dispatchers.Main.immediate) {

    }

    customScope.actor<Int> {

    }
    delay(100000)

}

