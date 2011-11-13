from mod_python import apache
import modpythonhandler

def handler(req):
	    req.subprocess_env['PATH_INFO'] = req.subprocess_env['SCRIPT_URL']
	    return modpythonhandler.handler(req)
