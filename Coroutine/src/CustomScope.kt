import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

class CustomScope : CoroutineScope {

    private var parentJob = Job()

    override val coroutineContext: CoroutineContext
        get() = parentJob

    
    fun onStart() {
        parentJob = Job()
    }

    fun onStop() {
        parentJob.cancel()
    }

}