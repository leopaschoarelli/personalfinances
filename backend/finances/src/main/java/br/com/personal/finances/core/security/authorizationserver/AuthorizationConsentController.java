package br.com.personal.finances.core.security.authorizationserver;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequiredArgsConstructor
public class AuthorizationConsentController {

    private final RegisteredClientRepository registeredClientRepository;
    private final OAuth2AuthorizationConsentService consentService;

    @GetMapping("/oauth2/consent")
    public String consent(Principal principal,
                          Model model,
                          @RequestParam(OAuth2ParameterNames.CLIENT_ID) String clientId,
                          @RequestParam(OAuth2ParameterNames.SCOPE) String scope,
                          @RequestParam(OAuth2ParameterNames.STATE) String state) {

        var client = this.registeredClientRepository.findByClientId(clientId);

        if (client == null) {
            throw new AccessDeniedException(String.format("Client %s not found", clientId));
        }

        var consent = this.consentService.findById(client.getId(), principal.getName());

        var scopeArray = StringUtils.delimitedListToStringArray(scope, " ");
        var scopesToApprove = new HashSet<>(Set.of(scopeArray));

        Set<String> scopesApprovedPreviously = Collections.emptySet();
        if (consent != null) {
            scopesApprovedPreviously = consent.getScopes();
            scopesToApprove.removeAll(scopesApprovedPreviously);
        }

        model.addAttribute("clientId", clientId);
        model.addAttribute("state", state);
        model.addAttribute("principalName", principal.getName());
        model.addAttribute("scopesToApprove", scopesToApprove);
        model.addAttribute("scopesApprovedPreviously", scopesApprovedPreviously);

        return "";
    }

}