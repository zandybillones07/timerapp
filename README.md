# Simple timer app

Libraries used<br />
I only use the lifecycle arch for this app. I also did'nt use the kotin-extension as it's already deprecated. <br/>
<br />
Architecture flow (MVVM)<br />
View <-> ViewModel <-> Repository <-> Local/Remote Data Source<br />
<br />
Features:<br />
  - The timer is displayed as hh:mm:ss. Tapping the button should stop/reset the timer. When activity is destroyed, timer should be able to resume the counting.
<br />
Possible additional features:<br />
- I could use view bindings and implement the single activity architecture. i also might use coroutines or rxjava but the requirement is pretty much basic and straight forward<br/>
- I could also use the chronometer widget instead. but, i prefer this approach to cater the mvvm structure

<br/>
<br/>
<br/>
Thank you!
