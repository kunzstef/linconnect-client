/*    
 	LinConnect: Mirror Android notifications on Linux Desktop

    Copyright (C) 2013  Will Hauck

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

package com.willhauck.linconnectclient;

import android.accessibilityservice.AccessibilityService;
import android.app.Notification;
import android.content.Context;
import android.os.AsyncTask;
import android.view.accessibility.AccessibilityEvent;

public class OldNotificationService extends AccessibilityService {

    public OldNotificationService() {
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent arg0) {
        if (!(arg0.getParcelableData() instanceof Notification)) {
            return;
        }
        final Notification notification = (Notification) arg0.getParcelableData();
        NotifyTask task = new NotifyTask();
        task.execute(getApplicationContext(), notification, arg0.getPackageName().toString());
    }

    @Override
    public void onInterrupt() {
    }

    protected static class NotifyTask extends AsyncTask<Object, Void, Boolean> {
        @Override
        protected Boolean doInBackground(Object... notif) {
            return NotificationUtilities.sendData((Context) notif[0], (Notification) notif[1], (String) notif[2]);
        }

        @Override
        protected void onPostExecute(Boolean result) {
        }
    }

}
