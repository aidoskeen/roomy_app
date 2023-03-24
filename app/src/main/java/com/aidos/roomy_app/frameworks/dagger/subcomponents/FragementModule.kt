package com.aidos.roomy_app.frameworks.dagger.subcomponents

import com.aidos.roomy_app.ui.admin_ui.AdminLoginFragment
import com.aidos.roomy_app.ui.admin_ui.AdminMenuFragment
import com.aidos.roomy_app.ui.admin_ui.MakeAnnouncementFragment
import com.aidos.roomy_app.ui.admin_ui.RequestsFragment
import com.aidos.roomy_app.ui.resident_ui.*
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragementModule {

    @ContributesAndroidInjector
    abstract fun contributeLoginFragment(): LoginFragment

    @ContributesAndroidInjector
    abstract fun contributeRegistrationFragment(): RegistrationFragment

    @ContributesAndroidInjector
    abstract fun contributeSplashFragment(): SplashFragment

    @ContributesAndroidInjector
    abstract fun contributeDormitoriesFragment(): DormitoriesFragment

    @ContributesAndroidInjector
    abstract fun contributeRequestsFragment(): RequestsFragment

    @ContributesAndroidInjector
    abstract fun contributeMakeAnnoucementFragment(): MakeAnnouncementFragment

    @ContributesAndroidInjector
    abstract fun contributeAdminLoginFragment(): AdminLoginFragment

    @ContributesAndroidInjector
    abstract fun contributeAdminMenuFragment(): AdminMenuFragment

    @ContributesAndroidInjector
    abstract fun contributeRoomsFragment(): RoomsFragment

    @ContributesAndroidInjector
    abstract fun contributeBookingFragment(): BookingFragment

    @ContributesAndroidInjector
    abstract fun contributeAnnouncementsFragment(): AnnouncementsFragment

    @ContributesAndroidInjector
    abstract fun contributeProfileFragment(): ProfileFragment

    @ContributesAndroidInjector
    abstract fun contributeMyRoomFragment(): MyRoomFragment

    @ContributesAndroidInjector
    abstract fun contributeInvoiceFragment(): InvoiceFragment
}