# AppAuth-Example

## How to use

To use this library or module, you have to set in the main AndroidManifest the entry point the 'com.skgtecnologia.helios.authenticationmodule.LogInActivity'
this activity will handle the authentication flow.

You have to provide with DI the module ' logInModule ' and you will have to provide an implementation of the "AuthenticationRouter".
The AuthenticationRouter will be responsible for the navigation out of the LogInActivity when the authentication process is success.

Example : 

<pre><code>
class AuthenticationRouterImpl(
private val context: Application,
) : AuthenticationRouter {
override fun onUserAuthenticated() {
val intent = Intent(context, MainActivity::class.java).apply {
flags = Intent.FLAG_ACTIVITY_NEW_TASK
}
context.startActivity(intent)
}
}
</code></pre>
