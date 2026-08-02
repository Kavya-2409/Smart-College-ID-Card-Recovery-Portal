 No lost ID reports filed yet. Use the form above to submit a report if you lost your ID card.
</td>
</tr>
<% } else { 
                            String statusNote = "";
                            if ("Found".equalsIgnoreCase(report.getStatus())) {
                                badgeClass = "status-found";
                                statusNote = "<div style='font-size:0.8rem; color:#2563eb; margin-top:4px;'>🎉 ID Located by Admin! Collect at Security Desk.</div>";
                                statusNote = "<div style='font-size:0.8rem; color:#2563eb; margin-top:4px;'>🎉 Located by Admin! Collect at Security Desk.</div>";
                            } else if ("Returned".equalsIgnoreCase(report.getStatus())) {
                                badgeClass = "status-returned";
                                statusNote = "<div style='font-size:0.8rem; color:#166534; margin-top:4px;'>✅ Case Closed: Returned to Student.</div>";
                                    <form action="LostReportServlet" method="post" style="margin:0;">
                                        <input type="hidden" name="action" value="resolveSelf">
                                        <input type="hidden" name="reportId" value="<%= report.getId() %>">
    <button type="submit" class="btn btn-sm btn-success" onclick="return confirm('Found your lost ID card? Mark this report as found & returned!');">🎉 Mark as Found</button>
    <button type="submit" class="btn btn-sm btn-success" onclick="return confirm('Mark your lost ID card as found?');">🎉 Mark as Found</button>
    </form>
    <% } else { %>
        <span style="color: var(--text-muted); font-size: 0.85rem;">Closed</span>
        <% } %>
            </td>
            </tr>
            <% 
                        } 
                    } 
                    %>
                <%   } 
                       } %>
                    </tbody>
                    </table>
                    </div>
                    </body>

                    </html>