package ykmc09.Idea2Slack;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import org.apache.commons.lang3.StringUtils;

import java.util.Optional;

public class Idea2Slack extends AnAction {

	@Override
	public void actionPerformed(AnActionEvent event) {

		try {
			Project project = event.getRequiredData(LangDataKeys.PROJECT);

			getSelectionText(event).ifPresent(s -> SlackClient.sendText(getSlackSettings(project).getWebhooksUrl(), s));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private SlackSettings getSlackSettings(Project project) {
		SlackSettings slackSettings = SlackSettings.getInstance();
		if (slackSettings.getWebhooksUrl() == null) {
			String url = Messages.showInputDialog(project, "Enter Incoming Webhooks Url", "Webhooks Url", null);
			slackSettings.setWebhooksUrl(url);
		}
		return slackSettings;
	}

	private Optional<String> getSelectionText(AnActionEvent event) {
		String selectedText = event.getRequiredData(LangDataKeys.EDITOR).getSelectionModel().getSelectedText();
		System.out.println("SELECTED TEXT: " + selectedText);

		return StringUtils.isBlank(selectedText) ? Optional.empty() : Optional.of(selectedText);
	}
}
