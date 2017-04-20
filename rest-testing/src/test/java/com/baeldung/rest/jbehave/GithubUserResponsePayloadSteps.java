package com.nklkarthi.rest.jbehave;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.nklkarthi.rest.GitHubUser;
import org.nklkarthi.rest.RetrieveUtil;
import org.hamcrest.Matchers;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import java.io.IOException;

import static com.nklkarthi.rest.jbehave.GithubUserNotFoundSteps.getGithubUserProfile;
import static org.hamcrest.MatcherAssert.assertThat;

public class GithubUserResponsePayloadSteps {

    private String api;
    private GitHubUser resource;

    @Given("github user profile api")
    public void givenGithubUserProfileApi() {
        api = "https://api.github.com/users/%s";
    }

    @When("I look for $user via the api")
    public void whenILookForEugenpViaTheApi(String user) throws IOException {
        HttpResponse httpResponse = getGithubUserProfile(api, user);
        resource = RetrieveUtil.retrieveResourceFromResponse(httpResponse, GitHubUser.class);
    }

    @Then("github's response contains a 'login' payload same as $username")
    public void thenGithubsResponseContainsAloginPayloadSameAsEugenp(String username) {
        assertThat(username, Matchers.is(resource.getLogin()));
    }

}
