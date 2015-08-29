package ykmc09.Idea2Slack;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.components.StoragePathMacros;
import org.jetbrains.annotations.Nullable;

/**
 *
 */
@State(
		name = "Idea2SlackSettings",
		storages = {
				@Storage(
						file = StoragePathMacros.APP_CONFIG + "/idea2slack_settings.xml"
				)
		}
)
public class SlackSettings implements PersistentStateComponent<SlackSettings> {
	private String webhooksUrl;

	@Nullable
	@Override
	public SlackSettings getState() {
		return this;
	}

	@Override
	public void loadState(SlackSettings slackSettings) {
		webhooksUrl = slackSettings.webhooksUrl;
	}

	public static SlackSettings getInstance() {
		return ServiceManager.getService(SlackSettings.class);
	}

	public String getWebhooksUrl() {
		return webhooksUrl;
	}

	public void setWebhooksUrl(String webhooksUrl) {
		this.webhooksUrl = webhooksUrl;
	}
}
