package nl.han.dea.ricky.controller;

import nl.han.dea.ricky.entity.Account;
import nl.han.dea.ricky.persistence.AccountDAO;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/dbTest")
public class TemporaryDAOTestController {

    @Inject
    AccountDAO accountDAO;

    @GET
    public Response testDB() {
        List<Account> accountList = new ArrayList<Account>();
        for (Account account : accountDAO.getAllAccounts()) {
            accountList.add(account);
        }
        return Response.status(Response.Status.OK).entity(accountList).build();
    }

}
