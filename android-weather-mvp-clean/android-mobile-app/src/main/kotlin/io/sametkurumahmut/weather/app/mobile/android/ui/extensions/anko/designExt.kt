package io.sametkurumahmut.weather.app.mobile.android.ui.extensions.anko

import android.support.v7.app.AppCompatActivity
import android.view.View

fun AppCompatActivity.indefiniteSnackbar(message: Int) =
        org.jetbrains.anko.design.indefiniteSnackbar(
                this.findViewById(android.R.id.content),
                message)

fun AppCompatActivity.indefiniteSnackbar(message: Int, actionText: Int, action: (View) -> Unit) =
        org.jetbrains.anko.design.indefiniteSnackbar(
                this.findViewById(android.R.id.content),
                message,
                actionText,
                action)

fun AppCompatActivity.indefiniteSnackbar(message: String) =
        org.jetbrains.anko.design.indefiniteSnackbar(
                this.findViewById(android.R.id.content),
                message)

fun AppCompatActivity.indefiniteSnackbar(
        message: String,
        actionText: String,
        action: (View) -> Unit) =
        org.jetbrains.anko.design.indefiniteSnackbar(
                this.findViewById(android.R.id.content),
                message,
                actionText,
                action)

fun AppCompatActivity.longSnackbar(message: Int) = org.jetbrains.anko.design.longSnackbar(
        this.findViewById(android.R.id.content),
        message)

fun AppCompatActivity.longSnackbar(message: Int, actionText: Int, action: (View) -> Unit) =
        org.jetbrains.anko.design.longSnackbar(
                this.findViewById(android.R.id.content),
                message,
                actionText,
                action)

fun AppCompatActivity.longSnackbar(message: String) = org.jetbrains.anko.design.longSnackbar(
        this.findViewById(android.R.id.content),
        message)

fun AppCompatActivity.longSnackbar(message: String, actionText: String, action: (View) -> Unit) =
        org.jetbrains.anko.design.longSnackbar(
                this.findViewById(android.R.id.content),
                message,
                actionText,
                action)

fun AppCompatActivity.snackbar(message: Int) = org.jetbrains.anko.design.snackbar(
        this.findViewById(android.R.id.content),
        message)

fun AppCompatActivity.snackbar(message: Int, actionText: Int, action: (View) -> Unit) =
        org.jetbrains.anko.design.snackbar(
                this.findViewById(android.R.id.content),
                message,
                actionText,
                action)

fun AppCompatActivity.snackbar(message: String) = org.jetbrains.anko.design.snackbar(
        this.findViewById(android.R.id.content),
        message)

fun AppCompatActivity.snackbar(message: String, actionText: String, action: (View) -> Unit) =
        org.jetbrains.anko.design.snackbar(
                this.findViewById(android.R.id.content),
                message,
                actionText,
                action)
