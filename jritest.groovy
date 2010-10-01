import org.rosuda.JRI.REXP
import org.rosuda.JRI.Rengine

Rengine engine = new Rengine(["--no-save"] as String[] , false, null)
engine.assign("a", [36] as int[] )
REXP result = engine.eval("sqrt(a)")
println result.asDouble()
engine.end()

