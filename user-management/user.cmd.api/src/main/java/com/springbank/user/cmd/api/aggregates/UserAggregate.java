package com.springbank.user.cmd.api.aggregates;

import com.springbank.user.cmd.api.commands.RegisterUserCommand;
import com.springbank.user.cmd.api.commands.RemoveUserCommand;
import com.springbank.user.cmd.api.commands.UpdateUserCommand;
import com.springbank.user.cmd.api.security.PasswordEncoder;
import com.springbank.user.cmd.api.security.PasswordEncoderImpl;
import com.springbank.user.core.events.UserRegisteredEvent;
import com.springbank.user.core.events.UserRemovedEvent;
import com.springbank.user.core.events.UserUpdateEvent;
import com.springbank.user.core.models.Account;
import com.springbank.user.core.models.User;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.UUID;

@Aggregate
public class UserAggregate {
    @AggregateIdentifier
    private String id;
    private User user;
    private final PasswordEncoder passwordEncoder;

    public UserAggregate() {
        passwordEncoder = new PasswordEncoderImpl();
    }

    @CommandHandler
    public UserAggregate(RegisterUserCommand command){
        passwordEncoder = new PasswordEncoderImpl();
        this.buildUser(command);
    }

    @CommandHandler
    public void handle(UpdateUserCommand command){
        this.buildUser(command);
    }

    @CommandHandler
    public void handle(RemoveUserCommand command){

    }

    @EventSourcingHandler
    public void on(UserRegisteredEvent event){
        this.id = event.getId();
        this.user = event.getUser();
    }

    @EventSourcingHandler
    public void on(UserUpdateEvent event){
        this.user = event.getUser();
    }

    @EventSourcingHandler
    public void on(UserRemovedEvent event){

    }

    private void buildUser(RegisterUserCommand command){
        User newUser = command.getUser();
        newUser.setId(command.getId());
        this.encodePassword(newUser);
        this.buildUserRegisteredEvent(command.getId(), newUser);
    }

    private void buildUser(UpdateUserCommand command){
        User newUser = command.getUser();
        newUser.setId(command.getId());
        this.encodePassword(newUser);
        this.buildUserUpdateEvent(command.getId(), newUser);
    }

    private void buildUserRegisteredEvent(String id, User user){
        AggregateLifecycle.apply(UserRegisteredEvent.builder().id(id).user(user).build());
    }

    private void buildUserUpdateEvent(String id, User user){
        AggregateLifecycle.apply(UserUpdateEvent.builder().id(UUID.randomUUID().toString())
                .user(user).build());
    }

    private void encodePassword(User user){
        String password = user.getAccount().getPassWord();
        user.getAccount().setPassWord(passwordEncoder.hashPassword(password));
    }

}
