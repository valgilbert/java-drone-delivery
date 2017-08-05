# Drone Delivery Analysis

## Analysis

- How did you implement your solution?
	- Ans. I implement my solution with SpringBoot and Java 8 Stream API
- Why did you implement it this way?
	- Ans. It is much easier to manipulate several list using the Stream API. We can take advantage of the several functions to manipulate and process collections.
- Let's assume we need to handle dispatching thousands of jobs per second to thousands of drivers. Would the solution you've implemented still work? Why or why not? What would you modify? Feel free to describe a completely different solution than the one you've developed.
	- Ans. Yes, This solution can handle high volume of request. Aside from the fact that streams are fast and efficient with bulk processing and parallel processing of data with stream operations. This application is design as a micro-service and can be easily be scaled accordingly.

