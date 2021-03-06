import { Component } from '@angular/core';

@Component({
  selector: 'sg-home',
  template: `<div class="container">
  <h2>Welcome to the HOBBIT Platform</h2>
  
  <p>The platform offers the benchmarking of systems and the execution of challenges. As a guest, you are welcome to browse through the results of experiments in which systems have been benchmarked and view the current status of the platform. You can also scroll through the challenges that are carried out by this platform.</p>
  <p>As registered User, you can benchmark your own systems that you have uploaded before. This can be done with already existing benchmarks or your own uploaded benchmarks. (Please note that there is a maximum runtime defined for this kind of benchmarking.)<br>
  Additionally, you can participate in challenges for which you have a system that fits into one of the challenge tasks.</p>
  <p>Finally, as a challenge organizer, you can create your own challenges which are preferred when it comes to the execution of experiments.</p>
  
  <p>For further information about the platform, <a href="https://github.com/hobbit-project/platform/wiki">please have a look into our wiki.</a> For more information about the HOBBIT project <a href="http://project-hobbit.eu/">visit our website</a></p>
  
  <p><span style="font-size:small">The HOBBIT project has received funding from the European Union's H2020 research and innovation action program under grant agreement number 688227. The project runtime is December 2015 until November 2018.</span></p>
</div>`
})
export class HomeComponent {
}
