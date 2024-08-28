/*
 * Copyright 2013 Signal Messenger, LLC
 * SPDX-License-Identifier: AGPL-3.0-only
 */
package org.whispersystems.textsecuregcm;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.core.Configuration;
import java.time.Duration;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.whispersystems.textsecuregcm.attachments.TusConfiguration;
import org.whispersystems.textsecuregcm.configuration.ApnConfiguration;
import org.whispersystems.textsecuregcm.configuration.ArtServiceConfiguration;
import org.whispersystems.textsecuregcm.configuration.AwsAttachmentsConfiguration;
import org.whispersystems.textsecuregcm.configuration.AwsCredentialsProviderFactory;
import org.whispersystems.textsecuregcm.configuration.BadgesConfiguration;
import org.whispersystems.textsecuregcm.configuration.BraintreeConfiguration;
import org.whispersystems.textsecuregcm.configuration.Cdn3StorageManagerConfiguration;
import org.whispersystems.textsecuregcm.configuration.CdnConfiguration;
import org.whispersystems.textsecuregcm.configuration.ClientCdnConfiguration;
import org.whispersystems.textsecuregcm.configuration.ClientReleaseConfiguration;
import org.whispersystems.textsecuregcm.configuration.DatadogConfiguration;
import org.whispersystems.textsecuregcm.configuration.DefaultAwsCredentialsFactory;
import org.whispersystems.textsecuregcm.configuration.DirectoryV2Configuration;
import org.whispersystems.textsecuregcm.configuration.DogstatsdConfiguration;
import org.whispersystems.textsecuregcm.configuration.DynamicConfigurationManagerFactory;
import org.whispersystems.textsecuregcm.configuration.DynamoDbClientFactory;
import org.whispersystems.textsecuregcm.configuration.DynamoDbTables;
import org.whispersystems.textsecuregcm.configuration.ExternalRequestFilterConfiguration;
import org.whispersystems.textsecuregcm.configuration.FaultTolerantRedisClusterFactory;
import org.whispersystems.textsecuregcm.configuration.FcmConfiguration;
import org.whispersystems.textsecuregcm.configuration.GcpAttachmentsConfiguration;
import org.whispersystems.textsecuregcm.configuration.GenericZkConfig;
import org.whispersystems.textsecuregcm.configuration.GooglePlayBillingConfiguration;
import org.whispersystems.textsecuregcm.configuration.HCaptchaClientFactory;
import org.whispersystems.textsecuregcm.configuration.KeyTransparencyServiceConfiguration;
import org.whispersystems.textsecuregcm.configuration.LinkDeviceSecretConfiguration;
import org.whispersystems.textsecuregcm.configuration.MaxDeviceConfiguration;
import org.whispersystems.textsecuregcm.configuration.MessageByteLimitCardinalityEstimatorConfiguration;
import org.whispersystems.textsecuregcm.configuration.MessageCacheConfiguration;
import org.whispersystems.textsecuregcm.configuration.NoiseWebSocketTunnelConfiguration;
import org.whispersystems.textsecuregcm.configuration.OneTimeDonationConfiguration;
import org.whispersystems.textsecuregcm.configuration.PaymentsServiceConfiguration;
import org.whispersystems.textsecuregcm.configuration.ProvisioningConfiguration;
import org.whispersystems.textsecuregcm.configuration.RegistrationServiceClientFactory;
import org.whispersystems.textsecuregcm.configuration.RemoteConfigConfiguration;
import org.whispersystems.textsecuregcm.configuration.ReportMessageConfiguration;
import org.whispersystems.textsecuregcm.configuration.S3ObjectMonitorFactory;
import org.whispersystems.textsecuregcm.configuration.SecureStorageServiceConfiguration;
import org.whispersystems.textsecuregcm.configuration.SecureValueRecovery2Configuration;
import org.whispersystems.textsecuregcm.configuration.SecureValueRecovery3Configuration;
import org.whispersystems.textsecuregcm.configuration.ShortCodeExpanderConfiguration;
import org.whispersystems.textsecuregcm.configuration.SpamFilterConfiguration;
import org.whispersystems.textsecuregcm.configuration.StripeConfiguration;
import org.whispersystems.textsecuregcm.configuration.SubscriptionConfiguration;
import org.whispersystems.textsecuregcm.configuration.TlsKeyStoreConfiguration;
import org.whispersystems.textsecuregcm.configuration.TurnConfiguration;
import org.whispersystems.textsecuregcm.configuration.UnidentifiedDeliveryConfiguration;
import org.whispersystems.textsecuregcm.configuration.VirtualThreadConfiguration;
import org.whispersystems.textsecuregcm.configuration.ZkConfig;
import org.whispersystems.textsecuregcm.limits.RateLimiterConfig;
import org.whispersystems.websocket.configuration.WebSocketConfiguration;

/** @noinspection MismatchedQueryAndUpdateOfCollection, WeakerAccess */
public class WhisperServerConfiguration extends Configuration {

  @NotNull
  @Valid
  @JsonProperty
  private TlsKeyStoreConfiguration tlsKeyStore;

  @NotNull
  @Valid
  @JsonProperty
  AwsCredentialsProviderFactory awsCredentialsProvider = new DefaultAwsCredentialsFactory();

  @NotNull
  @Valid
  @JsonProperty
  private StripeConfiguration stripe;

  @NotNull
  @Valid
  @JsonProperty
  private BraintreeConfiguration braintree;

  @NotNull
  @Valid
  @JsonProperty
  private GooglePlayBillingConfiguration googlePlayBilling;

  @NotNull
  @Valid
  @JsonProperty
  private DynamoDbClientFactory dynamoDbClient;

  @NotNull
  @Valid
  @JsonProperty
  private DynamoDbTables dynamoDbTables;

  @NotNull
  @Valid
  @JsonProperty
  private AwsAttachmentsConfiguration awsAttachments;

  @NotNull
  @Valid
  @JsonProperty
  private GcpAttachmentsConfiguration gcpAttachments;

  @NotNull
  @Valid
  @JsonProperty
  private CdnConfiguration cdn;

  @NotNull
  @Valid
  @JsonProperty
  private Cdn3StorageManagerConfiguration cdn3StorageManager;

  @NotNull
  @Valid
  @JsonProperty
  private DatadogConfiguration dogstatsd = new DogstatsdConfiguration();

  @NotNull
  @Valid
  @JsonProperty
  private FaultTolerantRedisClusterFactory cacheCluster;

  @NotNull
  @Valid
  @JsonProperty
  private ProvisioningConfiguration provisioning;

  @NotNull
  @Valid
  @JsonProperty
  private DirectoryV2Configuration directoryV2;

  @NotNull
  @Valid
  @JsonProperty
  private SecureValueRecovery2Configuration svr2;
  @NotNull
  @Valid
  @JsonProperty
  private SecureValueRecovery3Configuration svr3;

  @NotNull
  @Valid
  @JsonProperty
  private FaultTolerantRedisClusterFactory pushSchedulerCluster;

  @NotNull
  @Valid
  @JsonProperty
  private FaultTolerantRedisClusterFactory rateLimitersCluster;

  @NotNull
  @Valid
  @JsonProperty
  private MessageCacheConfiguration messageCache;

  @NotNull
  @Valid
  @JsonProperty
  private FaultTolerantRedisClusterFactory clientPresenceCluster;

  @Valid
  @NotNull
  @JsonProperty
  private List<MaxDeviceConfiguration> maxDevices = new LinkedList<>();

  @Valid
  @NotNull
  @JsonProperty
  private Map<String, RateLimiterConfig> limits = new HashMap<>();

  @Valid
  @NotNull
  @JsonProperty
  private WebSocketConfiguration webSocket = new WebSocketConfiguration();

  @Valid
  @NotNull
  @JsonProperty
  private FcmConfiguration fcm;

  @Valid
  @NotNull
  @JsonProperty
  private ApnConfiguration apn;

  @Valid
  @NotNull
  @JsonProperty
  private UnidentifiedDeliveryConfiguration unidentifiedDelivery;

  @Valid
  @NotNull
  @JsonProperty
  private HCaptchaClientFactory hCaptcha;

  @Valid
  @NotNull
  @JsonProperty
  private ShortCodeExpanderConfiguration shortCode;

  @Valid
  @NotNull
  @JsonProperty
  private SecureStorageServiceConfiguration storageService;

  @Valid
  @NotNull
  @JsonProperty
  private PaymentsServiceConfiguration paymentsService;

  @Valid
  @NotNull
  @JsonProperty
  private ArtServiceConfiguration artService;

  @Valid
  @NotNull
  @JsonProperty
  private ZkConfig zkConfig;

  @Valid
  @NotNull
  @JsonProperty
  private GenericZkConfig callingZkConfig;

  @Valid
  @NotNull
  @JsonProperty
  private GenericZkConfig backupsZkConfig;

  @Valid
  @NotNull
  @JsonProperty
  private RemoteConfigConfiguration remoteConfig;

  @Valid
  @NotNull
  @JsonProperty
  private DynamicConfigurationManagerFactory appConfig;

  @Valid
  @NotNull
  @JsonProperty
  private BadgesConfiguration badges;

  @Valid
  @JsonProperty
  @NotNull
  private SubscriptionConfiguration subscription;

  @Valid
  @JsonProperty
  @NotNull
  private OneTimeDonationConfiguration oneTimeDonations;

  @Valid
  @NotNull
  @JsonProperty
  private ReportMessageConfiguration reportMessage = new ReportMessageConfiguration();

  @Valid
  @JsonProperty
  private SpamFilterConfiguration spamFilter;

  @Valid
  @NotNull
  @JsonProperty
  private RegistrationServiceClientFactory registrationService;

  @Valid
  @NotNull
  @JsonProperty
  private TurnConfiguration turn;

  @Valid
  @NotNull
  @JsonProperty
  private TusConfiguration tus;

  @Valid
  @NotNull
  @JsonProperty
  private ClientReleaseConfiguration clientRelease = new ClientReleaseConfiguration(Duration.ofHours(4));

  @Valid
  @NotNull
  @JsonProperty
  private MessageByteLimitCardinalityEstimatorConfiguration messageByteLimitCardinalityEstimator = new MessageByteLimitCardinalityEstimatorConfiguration(Duration.ofDays(1));

  @Valid
  @NotNull
  @JsonProperty
  private LinkDeviceSecretConfiguration linkDevice;

  @Valid
  @NotNull
  @JsonProperty
  private VirtualThreadConfiguration virtualThread = new VirtualThreadConfiguration(Duration.ofMillis(1));


  @Valid
  @NotNull
  @JsonProperty
  private S3ObjectMonitorFactory maxmindCityDatabase;

  @Valid
  @NotNull
  @JsonProperty
  private S3ObjectMonitorFactory callingTurnDnsRecords;

  @Valid
  @NotNull
  @JsonProperty
  private S3ObjectMonitorFactory callingTurnPerformanceTable;

  @Valid
  @NotNull
  @JsonProperty
  private S3ObjectMonitorFactory callingTurnManualTable;

  @Valid
  @NotNull
  @JsonProperty
  private NoiseWebSocketTunnelConfiguration noiseTunnel;

  @Valid
  @NotNull
  @JsonProperty
  private ExternalRequestFilterConfiguration externalRequestFilter;

  @Valid
  @NotNull
  @JsonProperty
  private KeyTransparencyServiceConfiguration keyTransparencyService;

  public TlsKeyStoreConfiguration getTlsKeyStoreConfiguration() {
    return tlsKeyStore;
  }

  public AwsCredentialsProviderFactory getAwsCredentialsConfiguration() {
    return awsCredentialsProvider;
  }

  public StripeConfiguration getStripe() {
    return stripe;
  }

  public BraintreeConfiguration getBraintree() {
    return braintree;
  }

  public GooglePlayBillingConfiguration getGooglePlayBilling() {
    return googlePlayBilling;
  }

  public DynamoDbClientFactory getDynamoDbClientConfiguration() {
    return dynamoDbClient;
  }

  public DynamoDbTables getDynamoDbTables() {
    return dynamoDbTables;
  }

  public HCaptchaClientFactory getHCaptchaConfiguration() {
    return hCaptcha;
  }

  public ShortCodeExpanderConfiguration getShortCodeRetrieverConfiguration() {
    return shortCode;
  }

  public WebSocketConfiguration getWebSocketConfiguration() {
    return webSocket;
  }

  public AwsAttachmentsConfiguration getAwsAttachmentsConfiguration() {
    return awsAttachments;
  }

  public GcpAttachmentsConfiguration getGcpAttachmentsConfiguration() {
    return gcpAttachments;
  }

  public FaultTolerantRedisClusterFactory getCacheClusterConfiguration() {
    return cacheCluster;
  }

  public ProvisioningConfiguration getProvisioningConfiguration() {
    return provisioning;
  }

  public SecureValueRecovery2Configuration getSvr2Configuration() {
    return svr2;
  }
  public SecureValueRecovery3Configuration getSvr3Configuration() {
    return svr3;
  }

  public DirectoryV2Configuration getDirectoryV2Configuration() {
    return directoryV2;
  }

  public SecureStorageServiceConfiguration getSecureStorageServiceConfiguration() {
    return storageService;
  }

  public MessageCacheConfiguration getMessageCacheConfiguration() {
    return messageCache;
  }

  public FaultTolerantRedisClusterFactory getClientPresenceClusterConfiguration() {
    return clientPresenceCluster;
  }

  public FaultTolerantRedisClusterFactory getPushSchedulerCluster() {
    return pushSchedulerCluster;
  }

  public FaultTolerantRedisClusterFactory getRateLimitersCluster() {
    return rateLimitersCluster;
  }

  public Map<String, RateLimiterConfig> getLimitsConfiguration() {
    return limits;
  }

  public FcmConfiguration getFcmConfiguration() {
    return fcm;
  }

  public ApnConfiguration getApnConfiguration() {
    return apn;
  }

  public CdnConfiguration getCdnConfiguration() {
    return cdn;
  }

  public Cdn3StorageManagerConfiguration getCdn3StorageManagerConfiguration() {
    return cdn3StorageManager;
  }

  public DatadogConfiguration getDatadogConfiguration() {
    return dogstatsd;
  }

  public UnidentifiedDeliveryConfiguration getDeliveryCertificate() {
    return unidentifiedDelivery;
  }

  public Map<String, Integer> getMaxDevices() {
    Map<String, Integer> results = new HashMap<>();

    for (MaxDeviceConfiguration maxDeviceConfiguration : maxDevices) {
      results.put(maxDeviceConfiguration.getNumber(),
                  maxDeviceConfiguration.getCount());
    }

    return results;
  }

  public PaymentsServiceConfiguration getPaymentsServiceConfiguration() {
    return paymentsService;
  }

  public ArtServiceConfiguration getArtServiceConfiguration() {
    return artService;
  }

  public ZkConfig getZkConfig() {
    return zkConfig;
  }

  public GenericZkConfig getCallingZkConfig() {
    return callingZkConfig;
  }

  public GenericZkConfig getBackupsZkConfig() {
    return backupsZkConfig;
  }

  public RemoteConfigConfiguration getRemoteConfigConfiguration() {
    return remoteConfig;
  }

  public DynamicConfigurationManagerFactory getAppConfig() {
    return appConfig;
  }

  public BadgesConfiguration getBadges() {
    return badges;
  }

  public SubscriptionConfiguration getSubscription() {
    return subscription;
  }

  public OneTimeDonationConfiguration getOneTimeDonations() {
    return oneTimeDonations;
  }

  public ReportMessageConfiguration getReportMessageConfiguration() {
    return reportMessage;
  }

  public SpamFilterConfiguration getSpamFilterConfiguration() {
    return spamFilter;
  }

  public RegistrationServiceClientFactory getRegistrationServiceConfiguration() {
    return registrationService;
  }

  public TurnConfiguration getTurnConfiguration() {
    return turn;
  }

  public TusConfiguration getTus() {
    return tus;
  }

  public ClientReleaseConfiguration getClientReleaseConfiguration() {
    return clientRelease;
  }

  public MessageByteLimitCardinalityEstimatorConfiguration getMessageByteLimitCardinalityEstimator() {
    return messageByteLimitCardinalityEstimator;
  }

  public LinkDeviceSecretConfiguration getLinkDeviceSecretConfiguration() {
    return linkDevice;
  }

  public VirtualThreadConfiguration getVirtualThreadConfiguration() {
    return virtualThread;
  }

  public S3ObjectMonitorFactory getMaxmindCityDatabase() {
    return maxmindCityDatabase;
  }

  public S3ObjectMonitorFactory getCallingTurnDnsRecords() {
    return callingTurnDnsRecords;
  }

  public S3ObjectMonitorFactory getCallingTurnPerformanceTable() {
    return callingTurnPerformanceTable;
  }

  public S3ObjectMonitorFactory getCallingTurnManualTable() {
    return callingTurnManualTable;
  }

  public NoiseWebSocketTunnelConfiguration getNoiseWebSocketTunnelConfiguration() {
    return noiseTunnel;
  }

  public ExternalRequestFilterConfiguration getExternalRequestFilterConfiguration() {
    return externalRequestFilter;
  }

  public KeyTransparencyServiceConfiguration getKeyTransparencyServiceConfiguration() {
    return keyTransparencyService;
  }
}
