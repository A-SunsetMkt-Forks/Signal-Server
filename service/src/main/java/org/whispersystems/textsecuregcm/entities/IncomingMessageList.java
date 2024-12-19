/*
 * Copyright 2013-2020 Signal Messenger, LLC
 * SPDX-License-Identifier: AGPL-3.0-only
 */
package org.whispersystems.textsecuregcm.entities;

import static com.codahale.metrics.MetricRegistry.name;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.PositiveOrZero;
import org.whispersystems.textsecuregcm.controllers.MessageController;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Metrics;

import java.util.List;
import jakarta.validation.Valid;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;

public record IncomingMessageList(@NotNull
                                  @Valid
                                  List<@NotNull @Valid IncomingMessage> messages,

                                  boolean online,

                                  boolean urgent,

                                  @PositiveOrZero
                                  @Max(MessageController.MAX_TIMESTAMP)
                                  long timestamp) {

  private static final Counter REJECT_DUPLICATE_RECIPIENT_COUNTER =
      Metrics.counter(
          name(MessageController.class, "rejectDuplicateRecipients"),
          "multiRecipient", "false");

  @JsonCreator
  public IncomingMessageList(@JsonProperty("messages") @NotNull @Valid List<@NotNull IncomingMessage> messages,
      @JsonProperty("online") boolean online,
      @JsonProperty("urgent") Boolean urgent,
      @JsonProperty("timestamp") long timestamp) {

    this(messages, online, urgent == null || urgent, timestamp);
  }

  @AssertTrue
  public boolean hasNoDuplicateRecipients() {
    boolean valid = messages.stream().filter(m -> m != null).map(IncomingMessage::destinationDeviceId).distinct().count() == messages.size();
    if (!valid) {
      REJECT_DUPLICATE_RECIPIENT_COUNTER.increment();
    }
    return valid;
  }
}
